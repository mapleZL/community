package com.ejavashop.core;

import payment.api.system.PaymentEnvironment;

public  class SignCfcaUtil {
	
	private static SignCfcaUtil scf = null;
	
	public static SignCfcaUtil getInstance() {

        try {
            if (scf != null) return scf;
            System.out.println("==========================================");
            System.out.println("China Payment & Clearing Network Co., Ltd.");
            System.out.println("Payment and Settlement System");
            System.out.println("Institution Simulator v1.8.1.4");
            System.out.println("==========================================");
//            String systemConfigPath = servletContextEvent.getServletContext().getInitParameter("system.config.path");
//            // Log4j
//            String log4jConfigFile = systemConfigPath + File.separatorChar + "log4j.xml";
//            System.out.println(log4jConfigFile);
//            DOMConfigurator.configure(log4jConfigFile);

            String paymentConfigPath = EjavashopConfig.CFCA_CONFIG_PATH;
            // 初始化支付环境
            PaymentEnvironment.initialize(paymentConfigPath);
            scf = new SignCfcaUtil();
            return scf;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
	}

}
