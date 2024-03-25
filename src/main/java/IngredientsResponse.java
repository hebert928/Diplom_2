import java.util.List;

public class IngredientsResponse {
    private List<Ingredient> data;

    public IngredientsResponse() {
    }

    public IngredientsResponse(List<Ingredient> ingredientList) {
        this.data = ingredientList;
    }

    public List<Ingredient> getData() {
        return data;
    }

    public String[] getIdList() {
        return data.stream()
                .map((obj) -> obj.getId())
                .toArray(String[]::new);
    }

    public void setData(List<Ingredient> ingredientList) {
        this.data = ingredientList;
    }
}
