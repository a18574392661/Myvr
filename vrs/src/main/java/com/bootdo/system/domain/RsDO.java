package com.bootdo.system.domain;

import java.io.Serializable;

public class RsDO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	
	//主键
		private Long id;
		//名字
		private String name;
		
		public RsDO() {
			
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
		
		
}
