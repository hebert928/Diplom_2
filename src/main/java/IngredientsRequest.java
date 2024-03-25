import com.google.gson.Gson;

public class IngredientsRequest {
    private String[] ingredients;

    public IngredientsRequest() {
    }

    public IngredientsRequest(String[] ingredients ) {
        this.ingredients = ingredients;
    }

    public String[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(String[] ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public java.lang.String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
