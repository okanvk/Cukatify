package com.okanciftci.cukatify.architectural;

import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.AbstractController;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

public class NamingConventionTest {

    @ArchTest
    static ArchRule services_should_be_prefixed =
            classes()
                    .that().resideInAPackage("..services..")
                    .and().areAnnotatedWith(Service.class)
                    .should().haveSimpleNameEndingWith("Service").orShould().haveSimpleNameEndingWith("ServiceImpl");

    @ArchTest
    static ArchRule controllers_should_not_have_Gui_in_name =
            classes()
                    .that().resideInAPackage("..controllers..")
                    .should().haveSimpleNameNotContaining("Gui");

    @ArchTest
    static ArchRule controllers_should_be_suffixed =
            classes()
                    .that().resideInAPackage("..controllers..")
                    .or().areAnnotatedWith(Controller.class)
                    .or().areAssignableTo(AbstractController.class)
                    .should().haveSimpleNameEndingWith("Controller");

    @ArchTest
    static ArchRule classes_named_controller_should_be_in_a_controller_package =
            classes()
                    .that().haveSimpleNameContaining("Controller")
                    .should().resideInAPackage("..controllers..");

}