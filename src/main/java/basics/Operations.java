package basics;

public class Operations {
    public static void main(String[] args) {
        System.out.println("-------------Task0---------------");
        task0(4,2);
        System.out.println("-------------Task1---------------");
        task1(9, 2.1);
        System.out.println("-------------Task2---------------");
        task2(15);
        System.out.println("-------------Task3---------------");
        task3();
        System.out.println("-------------Task4---------------");
        task4();
    }

    public static void task4() {
        int maxNumber = Integer.MAX_VALUE;
        int minNumber = Integer.MIN_VALUE;

        System.out.println("Overflow to plus: " + (minNumber - 1)); // 2_147_483_647 (переполнение в плюс)
        System.out.println("Overflow to minus: " + (maxNumber + 1)); // -2_147_483_648 (переполнение в минус)
    }

    public static void task3() {
        /*  Float:
            Минимальное значение ≈ 1.4e-45, можно получить с помощью Float.MIN_VALUE.
            Максимальное значение ≈ 3.4028235e+38, можно получить с помощью Float.MAX_VALUE.
            Double:
            Минимальное значение ≈ 4.9e-324, можно получить с помощью Double.MIN_VALUE.
            Максимальное значение ≈ 1.7976931348623157e+308, можно получить с помощью Double.MAX_VALUE.

            При переполнении чисел с плавающей точкой не возникает исключений, а происходит:
            - Переход в бесконечность (±Infinity) – если значение выходит за MAX_VALUE.
            - Потеря точности – если число становится слишком маленьким.
            - Получение NaN – при неопределённых операциях (например, 0.0 / 0.0).
        */
        double overflow = Double.MAX_VALUE * 2; // Выходим за пределы MAX_VALUE, получаем +Infinity
        System.out.println("Overflow in +Infinity: " + overflow);

        double inf = -1.0 / 0.0;  // Деление на ноль, получаем бесконечность (в данном случае отрицательную из-за делимого)
        System.out.println("Dividing by 0, overflow in -Infinity: " + inf);

        double nan = 0.0 / 0.0;  // Получаем Not-a-Number
        System.out.println("Getting NaN: " + nan);
    }

    public static void task2(int age) {
        if (age > 0 && age < 7) {
            System.out.println("You are a kindergarten student");
        } else if (age >= 7 && age < 18) {
            System.out.println("You are a school child");
        } else if (age >= 18 && age < 24) {
            System.out.println("You are a student");
        } else if (age >= 24 && age < 60) {
            System.out.println("You are an adult");
        } else if (age >= 60 && age < 110) {
            System.out.println("You are a pensioner");
        } else {
            System.out.println("Invalid value. Try again");
        }
    }

    public static void task1(int number1, double number2) {
        // Выполняется операция: (number1 + number2) * (number1 - number2)
        System.out.println("The result of the operation between int and double: "
                + multiply(sum(number1, number2), subtract(number1, number2)));
    }

    public static void task0(int number1, int number2) {
        System.out.println("The result of adding numbers: " + sum(number1, number2));
        System.out.println("The result of subtraction of numbers: " + subtract(number1, number2));
        System.out.println("The result of multiplying numbers: " + multiply(number1, number2));
        System.out.println("The result of dividing numbers: " + divide(number1, number2));
    }

    public static int sum(int number1, int number2) {
        return number1 + number2;
    }
    public static double sum(double number1, double number2) {
        return number1 + number2;
    }

    public static int subtract(int number1, int number2) {
        return number1 - number2;
    }
    public static double subtract(double number1, double number2) {
        return number1 - number2;
    }

    public static int multiply(int number1, int number2) {
        return number1 * number2;
    }
    public static double multiply(double number1, double number2) {
        return number1 * number2;
    }

    public static int divide(int number1, int number2) throws ArithmeticException {
        return number1 / number2;
    }
}
