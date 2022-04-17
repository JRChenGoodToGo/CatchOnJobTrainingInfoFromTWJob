package htmlTagDao;

import java.io.Serializable;
import java.util.List;

public class Rent591ItemsDao implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<String> dataBindIdList; 
	private List<String> itemSubjectList;
	private List<String> itemSpaceList;
	private List<String> itemAddressList;
	private List<String> itemCostList;
	private List<List<String>> itemListImgList;
	
	public Rent591ItemsDao(){
	}

	public List<String> getDataBindIdList() {
		return dataBindIdList;
	}

	public void setDataBindIdList(List<String> dataBindIdList) {
		this.dataBindIdList = dataBindIdList;
	}

	public List<String> getItemSubjectList() {
		return itemSubjectList;
	}

	public void setItemSubjectList(List<String> itemSubjectList) {
		this.itemSubjectList = itemSubjectList;
	}

	public List<String> getItemSpaceList() {
		return itemSpaceList;
	}

	public void setItemSpaceList(List<String> itemSpaceList) {
		this.itemSpaceList = itemSpaceList;
	}

	public List<String> getItemAddressList() {
		return itemAddressList;
	}

	public void setItemAddressList(List<String> itemAddressList) {
		this.itemAddressList = itemAddressList;
	}

	public List<String> getItemCostList() {
		return itemCostList;
	}

	public void setItemCostList(List<String> itemCostList) {
		this.itemCostList = itemCostList;
	}

	public List<List<String>> getItemListImgList() {
		return itemListImgList;
	}

	public void setItemListImgList(List<List<String>> itemListImgList) {
		this.itemListImgList = itemListImgList;
	}

	@Override
	public String toString() {
		return "Rent591ItemsDao [dataBindIdList=" + dataBindIdList + ", itemSubjectList=" + itemSubjectList
				+ ", itemSpaceList=" + itemSpaceList + ", itemAddressList=" + itemAddressList + ", itemCostList="
				+ itemCostList + ", itemListImgList=" + itemListImgList + "]";
	}
	
}
