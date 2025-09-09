import random
import time

# worker speed
workerRate = 50
workerAbilityRate = 4

# helpers
def eclipsisInput(inputType, question, *validResponses):
    inputted = None
    while True:
        try:
            if inputType == "int":
                inputted = int(input(question))
            elif inputType == "float":
                inputted = float(input(question))
            else:
                inputted = input(question)
            
            if validResponses:
                for i in range(len(validResponses)):
                    if validResponses[i] == inputted:
                        return inputted
                print(f"input invalid. valid inputs: {validResponses}")
            else:
                return inputted
                
        except ValueError:
            print(f"input type invalid. requested type: {inputType}")
        
        print()

sqft = 0
while not (sqft >= 500 and sqft <= 10000):
    sqft = eclipsisInput("int", "> sqft:     ")
    if sqft > 10000:
        print("too large, pick a smaller area")
        print()
    elif sqft < 500:
        print("too small, pick a larger area")
        print()

# run setup
workers = eclipsisInput("int", "> workers:  ", 1, 2, 3, 4, 5, 6)
print()

# main loop
remaining = sqft
timeTaken = 0
while remaining > 0:
    timeTaken += 1
    for i in range(workers):
        if remaining < workerRate:
            remaining = 0
        else:
            if random.randint(1, workerAbilityRate) == 1:
                remaining -= workerRate*2 + random.randint(-10, 10)
                
                message = random.randint(1, 3)
                if message == 1:
                    print(f":: Worker #{i+1} had an energy drink!")
                elif message == 2:
                    print(f":: Worker #{i+1} is feeling energized!")
                else:
                    print(f":: Worker #{i+1} is aiming for a promotion!")
            else:
                remaining -= workerRate + random.randint(-10, 10)
      
    if remaining > 0: 
        print(f"The workers still have to build {remaining}sqft.")
        print(f"The workers have been working for {timeTaken} hour(s).")
    else:
        print(f"The workers have finished building after {timeTaken} hour(s)!")
    
#   for i in range(int((sqft - remaining) / 25)):
#       if (i+1)%20 == 0:
#           print("* ")        
#       else:
#           print("* ", end='')
#   print() 
    
    time.sleep(0.5)
    print()
