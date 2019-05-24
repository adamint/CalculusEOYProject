package com.adamratzman.calculus.rules

enum class Theorem(
    val readable: String,
    val type: TheoremType?,
    supposeFuncMath: String,
    vararg conditionsMath: String,
    val math: String,
    val note: String? = null
) {
    CONTINUITY_POINT(
        "Continuity at a point",
        TheoremType.BASIC,
        "function \\(f\\)",
        "f(c) is defined",
        "\\(\\lim \\limits_{x \\to c} f(x)\\) exists",
        "\\(\\lim \\limits_{x \\to c} f(x) = f(c)\\)",
        math = "\\(f\\) is continuous at \\(c\\)"
    ),

    CONTINUITY_INTERVAL(
        "Continuity on a Closed Interval",
        TheoremType.BASIC,
        "function \\(f\\)",
        "\\(f\\) is continuous on \\((a,b)\\)",
        "\\(\\lim \\limits_{x \\to a^+} f(x) = f(a)\\)",
        "\\(\\lim \\limits_{x \\to b^-} f(x) = f(b)\\)",
        math = "\\(f\\) is continuous on \\([a,b]\\)"
    ),

    IVT(
        "Intermediate Value Theorem",
        TheoremType.BASIC,
        "function \\(f\\)",
        "${"f".math()} is continuous on ${"[a,b]".math()}",
        "${"f(a) \\ne".math()} ${"f(b)".math()}",
        "${"k".math()} is any number between ${Math("f(a)")} and ${Math("f(b)")}",
        math = "There is at least one number ${Math("c")} such that ${Math("f(c)=k")}"
    ),

    EVT(
        "Extreme Value Theorem",
        TheoremType.BASIC,
        "function ${Math("f")}",
        "${Math("f")} is continuous on a closed interval ${Math("[a,b]")}",
        math = "${Math("f")} has both a maximum and minimum on the interval"
    ),

    MVT(
        "Mean Value Theorem",
        TheoremType.BASIC,
        "function ${Math("f")}",
        "${Math("f")} is continuous on the closed interval ${Math("[a,b]")}",
        "${Math("f")} is differentiable on the open interval ${Math("(a,b)")}",
        math = "There exists a number ${Math("c")} in ${Math("(a,b)")} such that ${Math("f'(c)=\\dfrac {f(b)-f(a)}{b-a}")}"
    ),

    ROLLES(
        "Rolle's Theorem",
        TheoremType.BASIC,
        "function ${Math("f")}",
        "${Math("f")} is continuous on the closed interval ${Math("[a,b]")}",
        "${Math("f")} is differentiable on the open interval ${Math("(a,b)")}",
        "${Math("f(a)=f(b)")}",
        math = "There exists a number ${Math("c")} in ${Math("(a,b)")} such that ${Math("f'(c)=0")}"
    ),

    LIMIT_DEFINITION(
        "The Definition of a Limit",
        TheoremType.DERIVATIVE,
        "function ${Math("f")}",
        "${Math("f")} is differentiable at ${Math("x")}",
        math = "${Math("f'(x)= \\lim \\limits_{h \\to 0} \\dfrac {f(x+h)-f(x)}{h}")}"
    ),

    ALTERNATIVE_LIMIT_DEFINITION(
        "The Alternative Definition of a Limit",
        TheoremType.DERIVATIVE,
        "function ${Math("f")}",
        "${Math("f")} is differentiable at ${Math("x")}",
        math = "${Math("f'(a)= \\lim \\limits_{x \\to a} \\dfrac {f(x)-f(a)}{x-a}")}"
    ),

    LIM_DEF_INT(
        "The Limit Definition of Integrals",
        TheoremType.INTEGRAL,
        "function ${Math("f")}",
        "${Math("f")} is integrable on ${Math("(a,b)")}",
        math = "${Math("\\lim \\limits_{n \\to \\infty}\\sum_{i=1}^{n}f(c_i)\\Delta x_i = \\int_a^b f(x)dx")}"
    ),

    FUN_THEOREM(
        "The Fundamental Theorem of Calculus",
        TheoremType.INTEGRAL,
        "function ${Math("f")} and antiderivative ${Math("F")}",
        "${Math("f")} is integrable",
        math = "${Math("\\int_a^b f(x)dx = F(b) - F(a)")}"
    ),

    MVT_INT(
        "Mean Value Theorem for Integrals",
        TheoremType.INTEGRAL,
        "function ${Math("f")}",
        "${Math("f")} is continuous on the closed interval ${Math("[a,b]")}",
math = "There exists a number ${Math("c")} in the closed interval ${Math("[a,b]")} such that " +
        "${Math("\\int_a^b f(x)dx = f(c)(b-a)")}"
    ),

    AVG_VALUE_FUNC(
        "Average Value of a Function",
        TheoremType.INTEGRAL,
        "function ${Math("f")}",
        "${Math("f")} is integrable on the closed interval ${Math("[a,b]")}",
        math = "The average value of ${Math("f")} on the interval is ${Math("\\dfrac {1}{b-a}\\int_a^b f(x)dx")}"
    ),

    NET_CHANGE_THEOREM(
        "Net Change Theorem",
        TheoremType.INTEGRAL,
        "function ${Math("F")}",
        "${Math("F'")} is the derivative of ${Math("F")}",
        math = "${Math("\\int_a^b F'(x)dx = F(b) - F(a) =")} net change in ${Math("F")} from a to b"

    ),

    SECOND_FUN_THEOREM_CALC(
        "The Second Fundamental Theorem of Calculus",
        TheoremType.INTEGRAL,
        "function ${Math("f")}",
        "${Math("f")} is integrable",
        math = "${Math("\\dfrac {d}{dx} \\int_a^x f(t)dt=f(x)")}"
    ),

    L_HOPITAL(
        "L'Hôpital's Rule",
        TheoremType.BASIC,
        "functions ${Math("f")} and ${Math("g")}",
        "${Math("\\lim \\limits_{x \\to c} \\dfrac {f(x)}{g(x)}")} " +
                "is in an <a href='https://en.wikipedia.org/wiki/Indeterminate_form'>indeterminate form</a>",
        math = "${Math("\\lim \\limits_{x \\to c} \\dfrac {f(x)}{g(x)}=\\lim \\limits_{x \\to c} \\dfrac {f'(x)}{g'(x)}")}"
    )
    ;

    val conditions = "<b>Suppose $supposeFuncMath that satisfies all of the following.</b><br><ul class='uk-list'>" +
            conditionsMath.mapIndexed { i, condition -> "<li>${i + 1}: $condition</li>" }.joinToString("\n")
}

enum class TheoremType {
    INTEGRAL, BASIC, DERIVATIVE
}