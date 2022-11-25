# Parking Lot

> Cities are becoming smarter to meet our needs, and parking spaces are an essential part of urban design.

In this project, I created parking lot management program that keeps track of vacant spots and parked cars.

## Concepts

- Kotlin Basics (val/var variables, data types, if, for, while, when...)
- Mutable list
- Null safety
- OOP (classes, constructors, methods, atributes, inheritance, polymorphism)
- Errors and Exceptions

## Commands
| Command | Description |
| ------- | ----------- |
| create <SIZE> | Create parking lot with specified number of spots |
| status | Prints all occupied spots |
| park <CAR_ID> <COLOR> | Park a car by registration number and color |
| leave <SPOT> | Pick up the car |
| spot_by_color <COLOR> | Prints all space number of all the cars of a particular color |
| spot_by_reg <CAR_ID> | Returns the number of the spot where a car is located |
| reg_by_color <COLOR> | Prints all registration numbers of cars of a particular color |

## Example

```sh
> spot_by_color yellow
Sorry, a parking lot has not been created.
> create 4
Created a parking lot with 4 spots.
> park KA-01-HH-9999 White
White car parked in spot 1.
> park KA-01-HH-3672 White
White car parked in spot 2.
> park Rs-P-N-21 Red
Red car parked in spot 3.
> park Rs-P-N-22 Red
Red car parked in spot 4.
> reg_by_color GREEN
No cars with color GREEN were found.
> reg_by_color WHITE
KA-01-HH-9999, KA-01-HH-3672
> spot_by_color GREEN
No cars with color GREEN were found.
> spot_by_color red
3, 4
> spot_by_reg ABC
No cars with registration number ABC were found.
> spot_by_reg KA-01-HH-3672
2
> spot_by_reg Rs-P-N-21
3
> exit
```