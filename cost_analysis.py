import os
import re
import numpy as np
import matplotlib.pyplot as plt
from collections import defaultdict

# Ruta donde están los archivos
input_dir = './outputs'

# Diccionario para guardar los costos por grupo
costs_by_group = defaultdict(list)

# Regex para obtener el grupo:
# - "AStar_h1_output0.txt" => "AStar_h1"
# - "BFS_output0.txt" => "BFS"
group_pattern = re.compile(r'^([A-Za-z]+(?:_h\d)?)_output')

# Regex para extraer "Nodos solucion: <valor>" en varias formas
cost_pattern = re.compile(r'nodos\s+solucion\s*[:=]\s*(\d+)', re.IGNORECASE)

# Recorrer los archivos
for filename in os.listdir(input_dir):
    if filename.endswith('.txt'):
        match = group_pattern.match(filename)
        if not match:
            continue

        group = match.group(1)  # Ej: AStar_h1 o BFS

        file_path = os.path.join(input_dir, filename)
        with open(file_path, 'r', encoding='utf-8') as f:
            first_line = f.readline()
            cost_match = cost_pattern.search(first_line)
            if cost_match:
                cost = int(cost_match.group(1))
                costs_by_group[group].append(cost)

# Mapa de colores personalizado
color_map = {
    'BFS': 'blue',
    'DFS': 'orange',
    'IDDFS': 'pink',
    'Greedy_h1': 'green',
    'Greedy_h2': 'red',
    'AStar_h1': 'violet',
    'AStar_h2': 'brown'
}

# Orden deseado
order = ['BFS', 'DFS', 'IDDFS', 'Greedy_h1', 'Greedy_h2', 'AStar_h1', 'AStar_h2']

# Filtrar los grupos que tenemos en los datos según el orden dado
groups = [g for g in order if g in costs_by_group]

averages = [np.mean(costs_by_group[group]) for group in groups]
std_devs = [np.std(costs_by_group[group]) for group in groups]

colors = [color_map.get(group, 'gray') for group in groups]

# Graficar
plt.figure(figsize=(12, 6))
bars = plt.bar(groups, averages, yerr=std_devs, capsize=5, color=colors, edgecolor='black')

# Agregar etiquetas encima de cada barra, posicionando el texto arriba del error estándar
for bar, avg, std in zip(bars, averages, std_devs):
    yval = bar.get_height()
    plt.text(bar.get_x() + bar.get_width()/2, yval + std + 0.5, f'{avg:.0f}' if avg.is_integer() else f'{avg:.1f}', ha='center', va='bottom', fontsize=9)

# Estética
plt.ylabel('Costo promedio (# Nodos solución)')
plt.xlabel('Algoritmo')
plt.title('Comparación de Algoritmos por Costo de Solución')
plt.xticks(rotation=45)
# plt.grid(axis='y', linestyle='--', alpha=0.7)  # Líneas punteadas removidas
plt.tight_layout()
plt.show()
