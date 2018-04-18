package fr.inria.spirals.features.pattern;

import fr.inria.spirals.entities.MetricAnnotation;
import fr.inria.spirals.entities.RepairPatterns;
import fr.inria.spirals.features.spoon.CtElementAnalyzer;
import spoon.Launcher;
import spoon.reflect.code.CtInvocation;
import spoon.reflect.code.CtLiteral;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtType;
import spoon.reflect.declaration.CtTypedElement;
import spoon.reflect.factory.Factory;
import spoon.reflect.visitor.filter.TypeFilter;
import spoon.template.TemplateMatcher;

public class PatternMatcher {
    private static Factory templateFactory;

    static {
        Launcher launcher = new Launcher();
        launcher.addInputResource("./src/main/java/fr/inria/spirals/features/pattern/spoonPatternMatcher");
        launcher.buildModel();
        templateFactory = launcher.getFactory();
    }

    private CtElement e;

    public PatternMatcher(CtElement e) {
        this.e = e;
        e.getElements(new TypeFilter<>(CtTypedElement.class)).forEach(d -> {
            d.setType(null);
        });
    }

    public void analyze(RepairPatterns patterns, CtElementAnalyzer.ACTION_TYPE actionType) {
        if (e instanceof CtLiteral && actionType == CtElementAnalyzer.ACTION_TYPE.UPDATE)  {
            patterns.incrementMetric("constChange");
            return;
        }
        for (CtType<?> classTemplate: templateFactory.Class().getAll()) {
            for (CtMethod method: classTemplate.getMethods()) {
                method.getElements(new TypeFilter<>(CtTypedElement.class)).forEach(d -> {
                    if (d instanceof CtInvocation && "S".equals(((CtInvocation) d).getExecutable().getSimpleName())) {

                    } else {
                        d.setType(null);
                    }
                });
                TemplateMatcher matcher = new TemplateMatcher(method.getBody().getStatement(0));
                if (matcher.matches(e)) {
                    String pattern = method.getAnnotation(MetricAnnotation.class).key();
                    if (actionType == CtElementAnalyzer.ACTION_TYPE.DELETE && pattern.startsWith("wrap")) {
                        pattern = "un" + pattern.replace("wraps", "wrap");
                        if ("unwrapIf".equals(pattern)) {
                            pattern = "unwrapIfElse";
                        }
                    }
                    if (pattern.contains("condBlock")) {
                        if (actionType == CtElementAnalyzer.ACTION_TYPE.DELETE) {
                            pattern = "condBlock";
                        }
                        pattern += actionType.toString();
                    }
                    patterns.incrementMetric(pattern);
                }
            }
        }
    }
}
