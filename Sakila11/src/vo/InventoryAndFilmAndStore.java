package vo;

public class InventoryAndFilmAndStore {
	private Inventory inventory;
	private Film film;
	private Store store;
	
	public Inventory getInventory() {
		return inventory;
	}
	public Film getFilm() {
		return film;
	}
	public Store getStore() {
		return store;
	}
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	public void setStore(Store store) {
		this.store = store;
	}
	public void setFilm(Film film) {
		this.film = film;
	}
}
