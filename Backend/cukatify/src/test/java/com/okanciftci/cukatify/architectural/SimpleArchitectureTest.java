package com.okanciftci.cukatify.architectural;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchRules;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.GeneralCodingRules;

import static com.tngtech.archunit.core.domain.JavaClass.Predicates.resideInAPackage;
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
            .layer("Service").definedBy(simpleNameEndingWith("Service").or(simpleNameEndingWith("ServiceImpl")))
            .layer("Persistence").definedBy(simpleNameEndingWith("Repository"))
            .layer("Security").definedBy(resideInAPackage("com.okanciftci.cukatify.security").or(resideInAPackage("com.okanciftci.cukatify.security.jwt")))
            .layer("Test").definedBy(resideInAPackage("com.okanciftci.cukatify.unit_integration"))

            .whereLayer("Controller").mayOnlyBeAccessedByLayers("Test")
            .whereLayer("Service").mayOnlyBeAccessedByLayers("Controller","Service","Security","Test")
            .whereLayer("Persistence").mayOnlyBeAccessedByLayers("Service","Test");

    // acegi

    @ArchTest
    static final ArchRules NAMING_CONVENTION_RULES = ArchRules.in(NamingConventionTest.class);
}