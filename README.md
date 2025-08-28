
# TP1: Metodos de Busqueda

### Instrucciones de ejecucion:
Ejecutar el archivo run.sh desde el root del proyecto, con el algoritmo de busqueda deseado como argumento:
> `./run.sh <algoritmo>`

Por ejemplo: 
> `./run.sh BFS`

Los algoritmos disponibles son los siguientes:
- BFS, DFS, IDDFS

Para ejecutar un algoritmo que admite heuristica, se puede hacer de la siguiente forma:
> `./run.sh <algoritmo> <h1>`

donde h1 es la heuristica Manhattan, y cualquier otra opcion utilizara la segunda heuristica.

Los algoritmos que admiten heuristica disponibles son:
- Greedy, AStar
  
En caso de no contar con los permisos para ejecutarlo, otorgarselos:
> `chmod 777 ./run.sh`

