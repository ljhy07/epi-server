package com.example.product.domain.category.domain.value;

public enum TopCategory {
    Fashion("패션"),
    Beauty("뷰티"),
    ChildbirthAndInfant("출산/유아동"),
    Food("식품"),
    Kitchenware("주방용품"),
    HouseholdGoods("생활용품"),
    HomeInterior("홈인테리어"),
    HomeAppliancesAndDigital("가전디지털"),
    SportsAndLeisure("스포츠/레저"),
    CarSupplies("자동차용품"),
    BooksAndMusicAndDVD("도서/음반/DVD"),
    ToysAndHobbies("완구/취미"),
    StationeryAndOffice("문구/오피스"),
    PetSupplies("반려동물용품"),
    HealthAndHealthFood("헬스/건강식품");

    private final String topCategory;

    TopCategory(String topCategory) {
        this.topCategory = topCategory;
    }

    public String getTopCategory() {
        return topCategory;
    }

    public static TopCategory fromValue(String value) {
        for (TopCategory topCategory : TopCategory.values()) {
            if (topCategory.name().equalsIgnoreCase(value) || topCategory.getTopCategory().equalsIgnoreCase(value)) {
                return topCategory;
            }
        }
        throw new IllegalArgumentException("Invalid topCategory value: " + value);
    }
}
