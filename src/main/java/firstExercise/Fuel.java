package firstExercise;

import lombok.AllArgsConstructor;

/** Перечисление видов машин с информацией о расходе и стомости топлива за 1 л. */
@AllArgsConstructor
public enum Fuel {
    C100(12.5, 46.10),
    C200(12, 48.90),
    C300(11.5, 47.5),
    C400(20, 48.9);

    /** Расход топлива */
    double consumption;
    /** Стоимость топлива за 1 л. */
    double price;

    /** Получаем стоимость 1 км. пути */
    public double getFuelConsumption() {
        return (consumption * price) / 100;
    }
}
