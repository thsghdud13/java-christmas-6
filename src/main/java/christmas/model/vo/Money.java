package christmas.model.vo;

public record Money(int value) implements Comparable<Money> {

    public Money {
        validateValue(value);
    }

    private void validateValue(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("돈은 양수여야 함");
        }
    }

    public static Money max() {
        return new Money(Integer.MAX_VALUE);
    }

    @Override
    public int compareTo(Money o) {
        return Integer.compare(this.value, o.value);
    }

    public Money multiply(int count) {
        return new Money(value * count);
    }

    public Money add(Money money) {
        return new Money(value + money.value());
    }

    public Money sub(Money money) {
        return new Money(value - money.value());
    }

    @Override
    public int value() {
        return value;
    }
}
