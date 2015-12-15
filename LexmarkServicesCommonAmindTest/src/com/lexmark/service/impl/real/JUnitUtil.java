package com.lexmark.service.impl.real;

import java.lang.reflect.Method;

import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

/**
 * JUnit built-in versions: 
 * <ul>
 *   <li>Indigo: 4.8 
 *   <li>Juno: 4.10
 * </ul> 
 * 
 * @author vpetruchok
 * @version 1.0, 2013-02-20
 */
public class JUnitUtil {
    
    public static class TestExecutionInfoRule implements MethodRule {

        @Override
        public Statement apply(final Statement base, final FrameworkMethod m, final Object obj) {
            return new Statement() {

                @Override
                public void evaluate() throws Throwable {
                    long t0 = System.nanoTime();
                    try {
                        base.evaluate();
                    } finally {
                        Class<?> cl = m.getClass(); Method meth = m.getMethod();
                        String testDesc = meth.getDeclaringClass().getSimpleName() + "." + meth.getName(); 
                        System.out.println("--");
                        System.out.printf("[%s: exec time=%.3f sec.]\n",
                               testDesc, (System.nanoTime() - t0) / 1000000000.0);
                    }
                }
            };            
        }
   } 

}
