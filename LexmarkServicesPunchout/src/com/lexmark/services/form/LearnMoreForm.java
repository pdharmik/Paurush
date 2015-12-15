package com.lexmark.services.form;

import java.util.List;


public class LearnMoreForm {
	private Bullet bullet;
	private List<Bullet> bulletList;
	public List<Bullet> getBulletList() {
		return bulletList;
	}
	public void setBulletList(List<Bullet> bulletList) {
		this.bulletList = bulletList;
	}
	private Marketing marketing;
	private TechSpec techSpec;
	
	
	/**
	 * @param bullet 
	 */
	public void setBullet(Bullet bullet) {
		this.bullet = bullet;
	}
	/**
	 * @return bullet 
	 */
	public Bullet getBullet() {
		return bullet;
	}
	/**
	 * @param marketing 
	 */
	public void setMarketing(Marketing marketing) {
		this.marketing = marketing;
	}
	/**
	 * @return marketing 
	 */
	public Marketing getMarketing() {
		return marketing;
	}
	/**
	 * @param techSpec 
	 */
	public void setTechSpec(TechSpec techSpec) {
		this.techSpec = techSpec;
	}
	/**
	 * @return techSpec
	 */
	public TechSpec getTechSpec() {
		return techSpec;
	}
	
}
