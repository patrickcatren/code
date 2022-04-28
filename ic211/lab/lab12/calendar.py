"""
The class Calendar implements a calendar.
"""

class Calendar(object):

    months = (31,28,31,30,31,30,31,31,30,31,30,31)

    @staticmethod
    def leapyear(year):
        """
        The method leapyear returns True if the parameter year
        is a leap year, False otherwise
        """
        if not year % 4 == 0:
            return False
        elif not year % 100 == 0:
            return True
        elif not year % 400 == 0:
            return False
        else:
            return True


    def __init__(self, d, m, y):
        """
        d, m, y have to be integer values and year has to be
        a four digit year number
        """

        self.set_Calendar(d,m,y)


    def set_Calendar(self, d, m, y):
        """
        d, m, y have to be integer values and year has to be
        a four digit year number
        """

        if type(d) == int and type(m) == int and type(y) == int:
            self.days = d
            self.months = m
            self.years = y
        else:
            raise TypeError("d, m, y have to be integers!")


    def __str__(self):
        return "{0:02d}/{1:02d}/{2:4d}".format(self.months,
                                                   self.days,
                                                   self.years)



    def advance(self):
        """
        This method advances to the next date.
        """

        max_days = Calendar.months[self.months-1]
        if self.months == 2 and Calendar.leapyear(self.years):
            max_days += 1
        if self.days == max_days:
            self.days= 1
            if self.months == 12:
                self.months = 1
                self.years += 1
            else:
                self.months += 1
        else:
            self.days += 1
