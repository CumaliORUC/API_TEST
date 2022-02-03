package com.techproed.pojos;
/*
{
                      "userId": 21,
                      "id": 201,
                      "title": "Tidy your room",
                      "completed": false
                    }


    //1-Json objecten gelen tum keyleri private bir değişken olarak tanımlıyorum.
    //bu ornekte request le response aynı olduğu için tek pojo oluşturcaz.
    //2- Her değişken için getter and setter methodlar oluşturuyoruz.
    //3- Parametrsiz Cons. oluştur.
    //4- Parametreli Cons. oluştur.
    //5- Burdaki değerleri okumak için To-String methodu oluşturuyoruz.
 */



public class TodosPojo {
    private int userId;
    private int id;
    private String title;
    private boolean completed;

    @Override
    public String toString() {
        return "TodosPojo{" +
                "userId=" + userId +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                '}';
    }

    public TodosPojo(int userId, int id, String title, boolean completed) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.completed = completed;
    }

    public TodosPojo() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
