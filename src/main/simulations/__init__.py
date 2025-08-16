import matplotlib.pyplot as plt
import matplotlib.patches as patches
import matplotlib.animation as animation


def load_states(filename: str):
    """Reads states from resources/states.txt and returns a list of 3x3 matrices."""
    states = []
    current = []

    with open(filename, "r") as f:
        for line in f:
            line = line.strip()
            if not line:  # empty line means state separator
                if current:
                    states.append(current)
                    current = []
                continue

            row = list(map(int, line.split()))
            current.append(row)

        if current:  # last state if no trailing newline
            states.append(current)

    return states


def draw_state(ax, state):
    """Draw a single 3x3 puzzle state on the matplotlib axis."""
    ax.clear()
    ax.set_xlim(0, 3)
    ax.set_ylim(0, 3)
    ax.set_xticks([])
    ax.set_yticks([])
    ax.set_aspect('equal')

    for i in range(3):
        for j in range(3):
            value = state[i][j]
            if value != -1:
                rect = patches.Rectangle((j, 2 - i), 1, 1, facecolor="skyblue", edgecolor="black")
                ax.add_patch(rect)
                ax.text(j + 0.5, 2 - i + 0.5, str(value), ha="center", va="center",
                        fontsize=20, weight="bold")
            else:
                rect = patches.Rectangle((j, 2 - i), 1, 1, facecolor="white", edgecolor="black")
                ax.add_patch(rect)


def save_animation(filename="../resources/states.txt", out_file="puzzle.mp4", fps=1):
    states = load_states(filename)

    fig, ax = plt.subplots(figsize=(3, 3))

    def update(frame):
        draw_state(ax, states[frame])

    ani = animation.FuncAnimation(fig, update, frames=len(states), repeat=False)

    # Save as mp4 (requires ffmpeg installed)
    writer = animation.FFMpegWriter(fps=fps)
    ani.save(out_file, writer=writer)
    print(f"Animation saved to {out_file}")


if __name__ == "__main__":
    save_animation(fps=1)  # 1 frame per second (adjust speed here)
