-- helpers
local convertData = {
  ['a'] = ".-",
  ['b'] = "-...",
  ['c'] = "-.-.",
  ['d'] = "-..",
  ['e'] = ".",
  ['f'] = "..-.",
  ['g'] = "--.",
  ['h'] = "....",
  ['i'] = "..",
  ['j'] = ".---",
  ['k'] = "-.-",
  ['l'] = ".-..",
  ['m'] = "--",
  ['n'] = "-.",
  ['o'] = "---",
  ['p'] = ".--.",
  ['q'] = "--.-",
  ['r'] = ".-.",
  ['s'] = "...",
  ['t'] = "-",
  ['u'] = "..-",
  ['v'] = "...-",
  ['w'] = ".--",
  ['x'] = "-..-",
  ['y'] = "-.--",
  ['z'] = "--..",
  
  ['1'] = ".----",
  ['2'] = "..---",
  ['3'] = "...--",
  ['4'] = "....-",
  ['5'] = ".....",
  ['6'] = "-....",
  ['7'] = "--...",
  ['8'] = "---..",
  ['9'] = "----.",
  ['0'] = "-----",
  
  [' '] = "/"
}

function split(str)
  local substrs = {}
  -- "%S+" splits along whitespace
  for v in string.gmatch(str, "%S+") do
    table.insert(substrs, v)
  end
  return substrs
end

function search(searchTable, toSearch)
  for index, item in pairs(searchTable) do
    if item == toSearch then
      return index
    end
  end
  
  return "~"
end

-- morse to english
function morseToEnglish(morseString)
  local returnString = ""
  local seperated = split(morseString, " ")
  
  for i=1, #seperated do
    returnString = returnString .. search(convertData, seperated[i])
  end
  
  return returnString
end

-- english to morse
function englishToMorse(englishString)
  local returnString = ""
  
  for i=1, #englishString do
    returnString = returnString .. convertData[string.sub(englishString, i, i)] .. " "
  end

  return returnString
end
