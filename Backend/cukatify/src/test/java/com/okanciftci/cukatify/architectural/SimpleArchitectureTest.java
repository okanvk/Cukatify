package com.okanciftci.cukatify.architectural;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchRules;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.GeneralCodingRules;

import static com.tngtech.archunit.core.domain.JavaClass.Predicates.simpleNameEndingWith;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

@AnalyzeClasses(packages = "com.okanciftci.cukatify")
public class SimpleArchitectureTest {

    @ArchTest
    static final ArchRule RULE_1 = GeneralCodingRules.NO_CLASSES_SHOULD_THROW_GENERIC_EXCEPTIONS;

    @ArchTest
    static final ArchRule RULE_2 = GeneralCodingRules.NO_CLASSES_SHOULD_ACCESS_STANDARD_STREAMS;

    @ArchTest
    static final ArchRule LAYER_RULES = layeredArchitecture()
            .layer("Controller").definedBy(simpleNameEndingWith("Controller"))
            .layer("Service").definedBy(simpleNameEndingWith("Service"))
            .layer("Persistence").definedBy(simpleNameEndingWith("Repository"))

            .whereLayer("Controller").mayNotBeAccessedByAnyLayer()
            .whereLayer("Service").mayOnlyBeAccessedByLayers("Controller","Service")

            .whereLayer("Persistence").mayOnlyBeAccessedByLayers("Service");


    @ArchTest
    static final ArchRules NAMING_CONVENTION_RULES = ArchRules.in(NamingConventionTest.class);
}