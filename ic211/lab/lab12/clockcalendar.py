from clock import Clock
from calendar import Calendar

class ClockCalendar(Clock, Calendar):
    def __init__(self, month, day, year, hour, minute, second):
        Calendar.__init__(self, day, month, year)
        Clock.__init__(self, hour, minute, second)
    def __str__(self):
        return(Calendar.__str__(self) + "," + Clock.__str__(self))
    def tick(self):
        Clock.tick(self)
        if(self.hours == 0 and self.minutes == 0 and self.seconds == 0):
            Calendar.advance(self)
            








c = ClockCalendar(4, 23, 2019, 9, 55, 0)
print(c.days)
print(c.months)
print(c.years)
print(c.hours)
print(c.minutes)
print(c.seconds)
x = ClockCalendar(12,31,2013,23,59,59)
print("One tick from ",x, end=" ")
x.tick()
print("to ", x)

x = ClockCalendar(2,28,1900,23,59,59)
print("One tick from ",x, end=" ")
x.tick()
print("to ", x)

x = ClockCalendar(2,28,2000,23,59,59)
print("One tick from ",x, end=" ")
x.tick()
print("to ", x)

x = ClockCalendar(2,7,2013,13,55,40)
print("One tick from ",x, end=" ")
x.tick()
print("to ", x)
