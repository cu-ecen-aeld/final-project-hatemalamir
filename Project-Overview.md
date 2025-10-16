
# Overview
This project is a continuation of all lessons and projects in the Coursera [Advanced Embedded Linux Development](https://www.coursera.org/specializations/advanced-embedded-linux-development). It puts together everything I've learned and adds to it to take this knowledge a tiny step closer to real life applications. This project builds on the last assignment as follows:
- Targeting a real hardware platform, Raspberry Pi 4.
- Setting up networking and connecting remotely to the hardwared platform to send commands to the TCP server.
- Adding new commands to capture images through an attached camera and returning the image to the caller.
- Switching from Buildroot to Yocto.


# Target Build System
Yocto will be used to build the Kernel, application, and all other components.


# Hardware Platform
Raspberry Pi 4 is used for deployment and final testing. QEMU is used for testing before deployment.
Some initial setup is required for the Raspberry Pi:
- [Build basic Yocto image for RaspberryPi](https://github.com/cu-ecen-5013/yocto-assignments-base/wiki/Build-basic-YOCTO-image-for-RaspberryPi)
- [Setup Wifi and ssh on Raspberry Pi with Yocto Image](https://github.com/cu-ecen-5013/yocto-assignments-base/wiki/WiFi-and-ssh-Configuration-on-RaspberryPi-with-Yocto-Image)


# Open Source Projects Used
So far, no open source projects are used, beyond the base platform buildroot/yocto packages already used in assignments. Will keep this udpated with any changes.


# Previously Discussed Content
TBD: Mention any content covered in previous assignments, lectures, or book sections you plan to use with the project as a basis. For instance, if you plan to use the aesdsocket or aesdchar componets either partially or completely.

This projects uses Assignment 9 as a basis, which includes both `aesdsocket` and `aesdchar` components, but extends their functionalities by adding new commands. A camera is used in this project, but the driver is plug-and-play and the file system entry (i.e. `/dev/video*`) is ready to use.


# New Content
Event Poll from Chatper 4 in the Advanced Linux Programming book is used to enahnce the desgin of `aesdsocket`. The idea is to use only as many threads as the machine that runs the code has, not a thread per connection but not a single thread either, to achieve real parallelism. The main thread controls the logic flow and creates events to monitor network and file IO. When data is available on any file descriptor to be read or written without blocking, it checks for an available thread to perform the operation.


# Shared Material
No material from other coursework or previous semesters is used in this project.


# Source Code Organization
Yocto Repository will be hosted at https://github.com/cu-ecen-aeld/final-project-hatemalamir

AESD application code will be hosted in in a repository at https://github.com/cu-ecen-aeld/assignments-3-and-later-hatemalamir


# Schedule Page
[Schedule Page](https://github.com/users/hatemalamir/projects/1/views/1)

