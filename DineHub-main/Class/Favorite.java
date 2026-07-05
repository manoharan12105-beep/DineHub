package Class;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Favorite {
  private Long favId;
  private LocalDate addAt;
  private String note;

  static List<Favorite> favoriteList = new ArrayList<>();

  Favorite(Long favId, String note){
    this.favId=favId;
    this.note=note;
    this.addAt=LocalDate.now();
  }

    public String create( Favorite favorite){
       favoriteList.add(favorite);
       return "Food  as Added in favorites";
    }

    public Favorite getbyId(long id){
        for(Favorite favorite: favoriteList){
            if(favorite.favId==id){
                return favorite;
            }
        }
        return null;
    }

       public String update(Favorite favorite) {
    for (int i=0; i<favoriteList.size(); i++) {
        if (favoriteList.get(i).favId == favorite.favId) {
            favoriteList.set(i, favorite);
            return "updated food details";
        }
    }
    return "Not found food";
}

    public  String delete(long id){
         for (int i = 0; i < favoriteList.size(); i++) {
            if (favoriteList.get(i).favId.equals(id)) {
                favoriteList.remove(i);
                return "deleted food";
            }
        }
        return "not found";
    }

    public Long getFavId() {
      return favId;
    }

    public LocalDate getAddAt() {
      return addAt;
    }

    public String getNote() {
      return note;
    }

    public static List<Favorite> getFavoriteList() {
      return favoriteList;
    }

}