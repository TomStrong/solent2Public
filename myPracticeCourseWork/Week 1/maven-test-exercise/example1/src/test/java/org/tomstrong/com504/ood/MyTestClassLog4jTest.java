/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tomstrong.com504.ood;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * @author 5strot78
 */
public class MyTestClassLog4jTest {
    
    public MyTestClassLog4jTest(){
    }
    
    @Test
    public void aboutMeTest() {
        MyTestClassLog4j testclass1 = new MyTestClassLog4j();
        testclass1.writeAboutMe();
    }
    
}
