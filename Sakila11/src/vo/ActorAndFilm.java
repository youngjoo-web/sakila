package vo;

public class ActorAndFilm {
   private FilmActor filmActor;
   private Actor actor;
   private Film film;
   
   public FilmActor getFilmActor() {
      return filmActor;
   }
   public void setFilmActor(FilmActor filmActor) {
      this.filmActor = filmActor;
   }
   public Film getFilm() {
      return film;
   }
   public void setFilm(Film film) {
      this.film = film;
   }
   public Actor getActor() {
      return actor;
   }
   public void setActor(Actor actor) {
      this.actor = actor;
   }
}