package com.registration.service;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class EmailConfig {


		String str1= "{\r\n    \"personalizations\": [\r\n      "
				+ "  {\r\n            \"to\": [\r\n                {\r\n               "
				+ "     \"email\": \"";
		String str2="\"\r\n        "
						+ "        }\r\n            ],\r\n          "
						+ "  \"subject\": \"OTP for Login !\"\r\n      "
						+ "  }\r\n    ],\r\n    \"from\": {\r\n    "
						+ "    \"email\": \"hodal19238@jobbrett.com\"\r\n  "
						+ "  },\r\n    \"content\": [\r\n        {\r\n        "
						+ "    \"type\": \"text/plain\",\r\n        "
						+ "    \"value\": \"";
		String str3="\"\r\n    "
				+ "    }\r\n    ]\r\n}";
		
		public String getStr1() {
			return str1;
		}
		public void setStr1(String str1) {
			this.str1 = str1;
		}
		public String getStr2() {
			return str2;
		}
		public void setStr2(String str2) {
			this.str2 = str2;
		}
		public String getStr3() {
			return str3;
		}
		public void setStr3(String str3) {
			this.str3 = str3;
		}
		

}
