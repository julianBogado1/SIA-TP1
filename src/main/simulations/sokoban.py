import pygame
import sys
from moviepy.editor import ImageSequenceClip
import numpy as np

# Map characters to colors
COLORS = {
    ' ': (240, 240, 240),   # EMPTY
    '#': (50, 50, 50),      # WALL
    'B': (139, 69, 19),     # BOX
    'T': (0, 200, 0),       # TARGET
    'X': (200, 150, 0),     # BOX ON TARGET
    'Y': (0, 0, 200),       # PLAYER ON TARGET
    'P': (0, 0, 255),       # PLAYER
}

CELL_SIZE = 80
ROWS, COLS = int(sys.argv[2]), int(sys.argv[3])  # Get rows and cols from command line arguments

def load_frames(filename):
    """Load frames from a txt file. Each frame is separated by a blank line."""
    with open(filename, "r") as f:
        content = f.read().strip()
    
    parts = content.split("=== SOLUCIÓN ===")
    metadata = parts[0].strip() if len(parts) > 1 else ""
    raw_frames = parts[1].strip().split("\n\n") if len(parts) > 1 else []

    frames = []
    for raw in raw_frames:
        lines = raw.split("\n")
        frame = [list(line) for line in lines if line.strip() != ""]
        frames.append(frame)
    return frames

def draw_frame(screen, frame):
    for r, row in enumerate(frame):
        for c, val in enumerate(row):
            color = COLORS.get(val, (255, 0, 0))  # unknown -> red
            rect = pygame.Rect(c * CELL_SIZE, r * CELL_SIZE, CELL_SIZE, CELL_SIZE)
            pygame.draw.rect(screen, color, rect)
            pygame.draw.rect(screen, (200, 200, 200), rect, 2)  # grid lines

def main(filename, output_file="output2.mp4"):
    frames = load_frames(filename)
    pygame.init()
    screen = pygame.display.set_mode((COLS * CELL_SIZE, ROWS * CELL_SIZE))

    video_frames = []
    clock = pygame.time.Clock()

    for frame in frames:
        # Draw current frame
        screen.fill((0, 0, 0))
        draw_frame(screen, frame)
        pygame.display.flip()

        # Capture screen pixels into numpy array (RGB)
        pixels = pygame.surfarray.array3d(pygame.display.get_surface())
        pixels = np.transpose(pixels, (1, 0, 2))  # transpose to (height, width, RGB)
        video_frames.append(pixels)

        # Show each frame for ~1 second
        pygame.time.wait(1000)

        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                pygame.quit()
                sys.exit()

        clock.tick(30)

    pygame.quit()

    # Save video using imageio
    clip = ImageSequenceClip(video_frames, fps=1)
    clip.write_gif(sys.argv[4])
    print(f"Saved animation to {sys.argv[4]}")

if __name__ == "__main__":
    if len(sys.argv) < 5:
        print("Usage: python sokoban.py <file.txt> <rows> <cols> <output.mp4>")
    else:
        main(sys.argv[1])
