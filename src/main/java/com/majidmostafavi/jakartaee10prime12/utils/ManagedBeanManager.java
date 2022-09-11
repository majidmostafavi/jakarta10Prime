package com.majidmostafavi.jakartaee10prime12.utils;

import com.majidmostafavi.jakartaee10prime12.core.utils.Configuration;
import jakarta.enterprise.context.spi.CreationalContext;
import jakarta.enterprise.inject.spi.Bean;
import jakarta.enterprise.inject.spi.BeanManager;
import jakarta.faces.context.FacesContext;

import javax.naming.InitialContext;
import java.util.Set;

/**
 * @author Majid
 */
public class ManagedBeanManager {

    //todo: migrate to CDI
//   public static <T, E extends T> T lookup(Class<E> ejbImplementationClass) {
//        T bean;
//        InitialContext initialContext;
//        try {
//            initialContext = new InitialContext();
//        } catch (Exception e) {
//            throw new RuntimeException("Failed to start initial context.", e);
//        }
//        Class<T> businessInterface = (Class<T>) ejbImplementationClass.getInterfaces().getClass().getInterfaces()[0];
//        String beanName = ejbImplementationClass.getSimpleName() + "!" + ejbImplementationClass.getName();
//        try {
//            //TomEE with nejat2 context
//            bean = (T) initialContext.lookup(BEAN_MANAGER_PATH + beanName);
//        } catch (Exception anIgnored) {
//            //Glassfish
//            String complexBeanName = ejbImplementationClass.getSimpleName() + "!" + businessInterface.getName();
//            try {
//                bean = (T) initialContext.lookup(BEAN_MANAGER_PATH + complexBeanName);
//            } catch (Exception e) {
//                try {
//
//                    return EjbManagedBean.getBean(ejbImplementationClass);
//                } catch (Exception ex) {
//                    throw new RuntimeException("Could not look up EJB of type " + ejbImplementationClass.getName());
//                }
//            }
//        }
//        return bean;
//    }


    @SuppressWarnings("unchecked")
    public static <T> T findBean(String beanName) {
        FacesContext context = FacesContext.getCurrentInstance();
        return (T) context.getApplication().evaluateExpressionGet(context, "#{" + beanName + "}", Object.class);
    }
    private static BeanManager getBeanManager() {
       InitialContext initContext = null;
       try {
           initContext = new InitialContext();
           BeanManager beanManager= (BeanManager) initContext.lookup(BEAN_MANAGER_PATH);
           return beanManager;

       } catch (Exception e) {
           e.printStackTrace();
       }
       return null;
   }


    private final static String BEAN_MANAGER_PATH = Configuration.getProperty("BEAN_MANAGER_PATH");

    @SuppressWarnings("unchecked")
    public static <T> T lookupByClass(Class<T> clazz) {
        try {
            BeanManager beanManager = getBeanManager();
            Set<Bean<?>> beans = beanManager.getBeans(clazz);
            if (beans.isEmpty()) {
                return null;
            }
            Bean<T> bean = (Bean<T>) beans.iterator().next();
            T beanInstance = beanManager.getContext(bean.getScope()).get(bean);
            if (beanInstance != null) {
                return beanInstance;
            }
            CreationalContext<T> ct = beanManager.createCreationalContext(bean);
            return beanManager.getContext(bean.getScope()).get(bean, ct);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static <T> T lookupByName(String name) {
        try {
            BeanManager beanManager = getBeanManager();
            Set<Bean<?>> beans = beanManager.getBeans(name);
            if (beans.isEmpty()) {
                return null;
            }
            Bean<T> bean = (Bean<T>) beans.iterator().next();
            T beanInstance = beanManager.getContext(bean.getScope()).get(bean);
            if (beanInstance != null) {
                return beanInstance;
            }
            CreationalContext<T> ct = beanManager.createCreationalContext(bean);
            return beanManager.getContext(bean.getScope()).get(bean, ct);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

