package fr.inria.spirals.entities;

/**
 * Created by tdurieux
 */
public class RepairActions extends Metric {
    @MetricAnnotation(key = "assignAdd", name = "Assignment addition")
    private int assignAdd = 0;

    @MetricAnnotation(key = "assignRem", name = "Assignment removal")
    private int assignRem = 0;

    @MetricAnnotation(key = "assignExpChange", name = "Assignment expression modification")
    private int assignExpChange = 0;

    @MetricAnnotation(key = "condBranIfAdd", name = "Conditional (if) branch addition")
    private int condBranIfAdd = 0;

    @MetricAnnotation(key = "condBranIfElseAdd", name = "Conditional (if-else) branches addition")
    private int condBranIfElseAdd = 0;

    @MetricAnnotation(key = "condBranElseAdd", name = "Conditional (else) branch addition")
    private int condBranElseAdd = 0;

    @MetricAnnotation(key = "condBranCaseAdd", name = "Conditional (case in switch) branch addition")
    private int condBranCaseAdd = 0;

    @MetricAnnotation(key = "condBranRem", name = "Conditional (if or else) branch removal")
    private int condBranRem = 0;

    @MetricAnnotation(key = "condExpExpand", name = "Conditional expression expansion")
    private int condExpExpand = 0;

    @MetricAnnotation(key = "condExpRed", name = "Conditional expression reduction")
    private int condExpRed = 0;

    @MetricAnnotation(key = "condExpMod", name = "Conditional expression modification")
    private int condExpMod = 0;

    @MetricAnnotation(key = "loopAdd", name = "Loop addition")
    private int loopAdd = 0;

    @MetricAnnotation(key = "loopRem", name = "Loop removal")
    private int loopRem = 0;

    @MetricAnnotation(key = "loopCondChange", name = "Loop conditional expression modification")
    private int loopCondChange = 0;

    @MetricAnnotation(key = "loopInitChange", name = "Loop initialization field modification")
    private int loopInitChange = 0;

    @MetricAnnotation(key = "mcAdd", name = "Method call addition")
    private int mcAdd = 0;

    @MetricAnnotation(key = "mcRem", name = "Method call removal")
    private int mcRem = 0;

    @MetricAnnotation(key = "mcRepl", name = "Method call replacement")
    private int mcRepl = 0;

    // TODO
    @MetricAnnotation(key = "mcMove", name = "Method call moving")
    private int mcMove = 0;

    @MetricAnnotation(key = "mcParAdd", name = "Method call parameter addition")
    private int mcParAdd = 0;

    @MetricAnnotation(key = "mcParRem", name = "Method call parameter removal")
    private int mcParRem = 0;

    @MetricAnnotation(key = "mcParSwap", name = "Method call parameter value swapping")
    private int mcParSwap = 0;

    @MetricAnnotation(key = "mcParValChange", name = "Method call parameter value modification")
    private int mcParValChange = 0;

    @MetricAnnotation(key = "mdAdd", name = "Method definition addition")
    private int mdAdd = 0;

    @MetricAnnotation(key = "mdRem", name = "Method definition removal")
    private int mdRem = 0;

    @MetricAnnotation(key = "mdRen", name = "Method definition renaming")
    private int mdRen = 0;

    @MetricAnnotation(key = "mdParAdd", name = "Parameter addition in method definition")
    private int mdParAdd = 0;

    @MetricAnnotation(key = "mdParRem", name = "Parameter removal from method definition")
    private int mdParRem = 0;

    @MetricAnnotation(key = "mdParTyChange", name = "Parameter type modification in method definition")
    private int mdParTyChange = 0;

    @MetricAnnotation(key = "mdRetTyChange", name = "Method return type modification")
    private int mdRetTyChange = 0;

    @MetricAnnotation(key = "mdModChange", name = "Method modifier change")
    private int mdModChange = 0;

    @MetricAnnotation(key = "mdOverride", name = "Method overriding addition or removal")
    private int mdOverride = 0;

    @MetricAnnotation(key = "objInstAdd", name = "Object instantiation addition")
    private int objInstAdd = 0;

    @MetricAnnotation(key = "objInstRem", name = "Object instantiation removal")
    private int objInstRem = 0;

    @MetricAnnotation(key = "objInstMod", name = "Object instantiation modification")
    private int objInstMod = 0;

    @MetricAnnotation(key = "exTryCatchAdd", name = "undefined")
    private int exTryCatchAdd = 0;

    @MetricAnnotation(key = "exTryCatchRem", name = "undefined")
    private int exTryCatchRem = 0;

    @MetricAnnotation(key = "exThrowsAdd", name = "undefined")
    private int exThrowsAdd = 0;

    @MetricAnnotation(key = "exThrowsRem", name = "undefined")
    private int exThrowsRem = 0;

    @MetricAnnotation(key = "retBranchAdd", name = "Return statement addition")
    private int retBranchAdd = 0;

    @MetricAnnotation(key = "retRem", name = "Return statement removal")
    private int retRem = 0;

    @MetricAnnotation(key = "retExpChange", name = "Return expression modification")
    private int retExpChange = 0;

    @MetricAnnotation(key = "varAdd", name = "Variable addition")
    private int varAdd = 0;

    @MetricAnnotation(key = "varRem", name = "Variable removal")
    private int varRem = 0;

    @MetricAnnotation(key = "varTyChange", name = "Variable type change")
    private int varTyChange = 0;

    @MetricAnnotation(key = "varModChange", name = "Variable modifier change")
    private int varModChange = 0;

    @MetricAnnotation(key = "varReplVar", name = "Variable replacement by another variable")
    private int varReplVar = 0;

    @MetricAnnotation(key = "varReplMc", name = "Variable replacement by method call")
    private int varReplMc = 0;

    @MetricAnnotation(key = "tyAdd", name = "Type addition")
    private int tyAdd = 0;

    @MetricAnnotation(key = "tyImpInterf", name = "Type implemented interface modification")
    private int tyImpInterf = 0;
}
