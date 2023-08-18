package model;

public class Categories {
	private int categoriesID ;
	private String categoriesName;

	public Categories() {

	}

	public Categories(int categoriesID, String categoriesName) {
		this.categoriesID = categoriesID;
		this.categoriesName = categoriesName;
	}

	public int getCategoriesID() {
		return categoriesID;
	}

	public void setCategoriesID(int categoriesID) {
		this.categoriesID = categoriesID;
	}

	public String getCategoriesName() {
		return categoriesName;
	}

	public void setCategoriesName(String categoriesName) {
		this.categoriesName = categoriesName;
	}

	@Override
	public String toString() {
		return "Categories [categoriesID=" + categoriesID + ", categoriesName=" + categoriesName + "]";
	}


}
