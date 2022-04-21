README

    Lab06 is a program that creates dots to be used by the plotter utility, adds a new set of red, blue and green dots every N steps, where N is an argument passed by the command line or if no argument is given defaults to 100. It prints the current position of the dots in the format "x-value y-value color", which each line in this format representing an instance of a dot, and "done" when it has printed the current position of every dot. 

    This program uses encapsulation to keep methods specific to BlueDOt or GreenDot within themselves, and methods used for all the dots are kept in moving dot and extended so that using inheritance they can all access them. Each extension of moving dot uses information hiding to keep all fields private, and uses the different extensions of movingDot are polymorphism, which allows MovingDot to exist in the different forms of RedDOt, BlueDOt, and GreenDot.

    Our object ORiented design makes it easy to create new dots by simply extended MovingDot and created the desired movement with a super call of step and adding the unique steps for it to change direction. Color is assigned by adding a color letter after a super call of toString.