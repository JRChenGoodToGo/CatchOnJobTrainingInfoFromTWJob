package htmlTagDao;

import java.io.Serializable;
import java.util.List;

public class Rent591ItemDao implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String dataBindId; 
	private String itemSubject;
	private String itemSpace;
	private String itemAddress;
	private String itemCost;
	private List<String> itemListImg;
	
	public Rent591ItemDao() {
	}
	
	public String getDataBindId() {
		return dataBindId;
	}
	public void setDataBindId(String dataBindId) {
		this.dataBindId = dataBindId;
	}
	public String getItemSubject() {
		return itemSubject;
	}
	public void setItemSubject(String itemSubject) {
		this.itemSubject = itemSubject;
	}
	public String getItemSpace() {
		return itemSpace;
	}
	public void setItemSpace(String itemSpace) {
		this.itemSpace = itemSpace;
	}
	public String getItemAddress() {
		return itemAddress;
	}
	public void setItemAddress(String itemAddress) {
		this.itemAddress = itemAddress;
	}
	public String getItemCost() {
		return itemCost;
	}
	public void setItemCost(String itemCost) {
		this.itemCost = itemCost;
	}
	public List<String> getItemListImg() {
		return itemListImg;
	}
	public void setItemListImg(List<String> itemListImg) {
		this.itemListImg = itemListImg;
	}
	@Override
	public String toString() {
		return "Rent591Dao {"
				+ "\n\tdataBindId=" + dataBindId
				+ ", \n\titemSubject=" + itemSubject
				+ ", \n\titemSpace=" + itemSpace
				+ ", \n\titemAddress=" + itemAddress
				+ ", \n\titemCost=" + itemCost
				+ ", \n\titemListImg=" + itemListImg
				+ "\n}";
	}
}
