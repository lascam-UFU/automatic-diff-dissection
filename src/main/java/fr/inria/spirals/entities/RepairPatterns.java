package fr.inria.spirals.entities;

/**
 * Created by tdurieux
 */
public class RepairPatterns extends Metric {
    @MetricAnnotation(key="condBlockOthersAdd", name="Conditional block addition")
    private int condBlockOthersAdd = 0;

    @MetricAnnotation(key="condBlockRetAdd", name="Conditional block addition with return statement")
    private int condBlockRetAdd = 0;

    @MetricAnnotation(key="condBlockExcAdd", name="Conditional block addition with exception throwing")
    private int condBlockExcAdd = 0;

    @MetricAnnotation(key="condBlockRem", name="Conditional block removal")
    private int condBlockRem = 0;

    @MetricAnnotation(key="expLogicExpand", name="Logic expression expansion")
    private int expLogicExpand = 0;

    @MetricAnnotation(key="expLogicReduce", name="Logic expression reduction")
    private int expLogicReduce = 0;

    @MetricAnnotation(key="expLogicMod", name="Logic expression modification")
    private int expLogicMod = 0;

    @MetricAnnotation(key="expArithMod", name="Arithmetic expression modification")
    private int expArithMod = 0;

    @MetricAnnotation(key="wrapsIf", name="Wraps-with if statement")
    private int wrapsIf = 0;

    @MetricAnnotation(key="wrapsIfElse", name="Wraps-with if-else statement")
    private int wrapsIfElse = 0;

    @MetricAnnotation(key="wrapsElse", name="Wraps-with else statement")
    private int wrapsElse = 0;

    @MetricAnnotation(key="wrapsTryCatch", name="Wraps-with try-catch block")
    private int wrapsTryCatch = 0;

    @MetricAnnotation(key="wrapsMethod", name="Wraps-with method call")
    private int wrapsMethod = 0;

    @MetricAnnotation(key="wrapsLoop", name="Wraps-with loop")
    private int wrapsLoop = 0;

    @MetricAnnotation(key="unwrapIfElse", name="undefined")
    private int unwrapIfElse = 0;

    @MetricAnnotation(key="unwrapMethod", name="undefined")
    private int unwrapMethod = 0;

    @MetricAnnotation(key="unwrapTryCatch", name="undefined")
    private int unwrapTryCatch = 0;

    @MetricAnnotation(key="wrongVarRef", name="Wrong Variable Reference")
    private int wrongVarRef = 0;

    @MetricAnnotation(key="wrongMethodRef", name="Wrong Method Reference")
    private int wrongMethodRef = 0;

    @MetricAnnotation(key="missNullCheckP", name="Missing null check addition")
    private int missNullCheckP = 0;

    @MetricAnnotation(key="missNullCheckN", name="Missing non-null check addition")
    private int missNullCheckN = 0;

    @MetricAnnotation(key="singleLine", name="undefined")
    private int singleLine = 0;

    @MetricAnnotation(key="copyPaste", name="undefined")
    private int copyPaste = 0;

    @MetricAnnotation(key="constChange", name="undefined")
    private int constChange = 0;

    @MetricAnnotation(key="codeMove", name="undefined")
    private int codeMove = 0;

    @MetricAnnotation(key="notClassified", name="undefined")
    private int notClassified = 0;
}
