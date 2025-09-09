import pygame

TILE_SIZE = 40

# Colors
COLORS = {
    "#": (50, 50, 50),     # Wall
    " ": (230, 230, 230),  # Empty background
    "T": (0, 200, 0),      # Green
    "B": (150, 75, 0),     # Brown
    "P": (0, 0, 255),      # Blue
}

def draw_map(map_lines, filename="map_output.png"):
    rows = len(map_lines)
    cols = len(map_lines[0])

    width, height = cols * TILE_SIZE, rows * TILE_SIZE

    pygame.init()
    surface = pygame.Surface((width, height))

    for y, line in enumerate(map_lines):
        for x, char in enumerate(line):
            rect = pygame.Rect(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE)

            # Background for walls vs empty space
            if char == "#":
                pygame.draw.rect(surface, COLORS["#"], rect)
            else:
                pygame.draw.rect(surface, COLORS[" "], rect)

            # If it's a special tile, draw it inside
            if char in "TBP":
                inner_rect = rect.inflate(-TILE_SIZE//5, -TILE_SIZE//5)
                pygame.draw.rect(surface, COLORS[char], inner_rect)

            # Grid lines
            pygame.draw.rect(surface, (200, 200, 200), rect, 1)

    pygame.image.save(surface, filename)


def load_maps_from_file(filename="maps.txt"):
    with open(filename, "r") as f:
        content = f.read().strip()

    # Split maps by blank lines
    raw_maps = content.split("\n\n")
    maps = [map_str.split("\n") for map_str in raw_maps]
    return maps


if __name__ == "__main__":
    maps = load_maps_from_file("maps.txt")

    for i, m in enumerate(maps):
        out_file = f"map_{i}.png"
        draw_map(m, out_file)
        print(f"Saved {out_file}")

    pygame.quit()
