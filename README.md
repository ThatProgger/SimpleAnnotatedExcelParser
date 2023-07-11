# SimpleAnnotatedExcelParser

Маленькая и очень простая программа была создана с целью облегчить труд девушек экономического отдела.
Задача: 
Прочитать исходный Эксель документ, из полученной коллекции отфильтровать ненужное, сконвертировать и записать в Эксель документ в подобающем виде.

Программа очень проста, используется принцип IoC и все реализации создаются в точке входа.
## Authors

- [@ThatProgger](https://www.github.com//ThatProgger)


## Usage/Examples

1. Создать конфигурационный файл
```json
{
  "start" : 0,
  "end" : 0,
  "pathToRead" : "C:\\excel\\documentForRead.xlsx",
  "pathToWrite" : "C:\\excel\\documentForWrite.xlsx",
  "keyParam" : {"letter" : "L", "regexp" : "(?<=\\()[^\\)]+" },
  "nameParam" : {"letter" : "E"},
  "dateParam" : {"letter" : "A", "regexp" : "^\\S+", "mask" : "^2023-04-.+"},
  "groupParam" : {"letter" : "G", "ignore" : [
  "Group name 1",
  "Group name 2",
  "Group name 3" ]}
}
```

2. Запустить архив с аргументами:
```bash
java -jar .\jarname -c pathToConfigFile
```


## The MIT License (MIT)

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
