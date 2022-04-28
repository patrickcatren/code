from clock import Clock
from calendar import Calendar

class ClockCalendar(Clock, Calendar):
    def __init__(self, month, day, year, hour, minute, second):
        super().__init__(self, day, month, year)
        super().__init__(self, hour, minute, second)

c = ClockCalendar(4, 23, 2019, 9, 55, 0)
print(c.days)
print(c.months)
print(c.years)
print(c.hours)
print(c.minutes)
print(c.seconds)
