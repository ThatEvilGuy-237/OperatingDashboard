# Dashboard for Linux Server

### Data to Display:

- **CPU:**
  - Usage %
  - Temperature °C
  - Core load breakdown
  - Frequency (GHz)

- **GPU:**
  - Usage %
  - Temperature °C
  - VRAM usage (extra)
  - GPU load
  - Power usage

- **RAM:**
  - Usage %
  - Swap usage

- **Disk:**
  - Usage %
  - Disk I/O (read/write speed)
  - Disk health (SMART status)

- **Containers:**
  - CPU usage
  - RAM usage
  - Disk usage
  - Volumes:
    - Size
  - Ports
  - Address
  - Container logs
  - Restart status
  - Resource limit settings

- **Internet:**
  - Speed
  - Usage
  - Ping latency

### Actions the User Can Perform:

- **Login Dashboard (JWT):**
  - Username + password + secret key
  - Role-based access control (RBAC)(extra not in the first version)

- **Containers:**
  - On/Off containers
  - Rebuild container
  - Delete volumes/clear it
  - Delete containers
  - Delete images
  - View container logs

## Backend Implementation Plan:

- **Backend Framework**: Spring Boot.
- **System Interaction**: Linux scripts will be used to gather system data (CPU, GPU, RAM, Disk, Docker status, etc.), and these scripts will be called from within the Spring Boot services.
- **Docker Integration**: Docker Java Library will interact with Docker commands.
- **Security**: JWT will be used for authentication, Login with username + password + secret key.
- **Container Info**: Linux scripts will be used to get container information (CPU, RAM, Disk, etc.).
- **Database**: MySQL will be used as the database for storing system data.

## Common questions:
- **Why linux scripts?**: Linux comands are greate to collect system info. You can do it with the api but can be more confusing.
- **Why a database?**: To track historic of the system to track.

### Future:
- **User Roles and Permissions**: Implement user roles and permissions to control access to different sections of the dashboard.