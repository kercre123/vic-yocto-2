FROM debian:bookworm

ARG UID
ARG GID

RUN apt-get update && apt-get install -y sudo
RUN apt-get install -y \
    build-essential chrpath cpio debianutils diffstat expect file gawk gcc git iputils-ping libacl1 liblz4-tool locales python3 python3-git python3-jinja2 python3-pexpect python3-subunit socat texinfo unzip wget xz-utils zstd
RUN apt-get install -y \
    git-core gnupg flex bison gperf build-essential zip curl zlib1g-dev gcc-multilib g++-multilib libc6-dev-i386 lib32ncurses5-dev x11proto-core-dev libx11-dev lib32z-dev libxml-simple-perl libc6-dev libgl1-mesa-dev tofrodos libxml2-utils xsltproc genisoimage
RUN apt-get install -y \
    gawk chrpath texinfo p7zip-full android-sdk-libsparse-utils
RUN apt-get install -y \
    ruby subversion libssl-dev
RUN apt-get install -y \
    pkg-config
RUN apt-get install -y \
    nano

RUN wget https://go.dev/dl/go1.24.1.linux-amd64.tar.gz
RUN tar -zxf go1.24.1.linux-amd64.tar.gz
RUN mv go /usr/local/

# later versions require later glibc :(
RUN wget https://github.com/ccache/ccache/releases/download/v4.7.5/ccache-4.7.5-linux-x86_64.tar.xz
RUN tar -xf ccache-4.7.5-linux-x86_64.tar.xz
RUN mv ccache-4.7.5-linux-x86_64/ccache /usr/bin/
RUN chmod +x /usr/bin/ccache

RUN ln -sf /bin/bash /bin/sh
RUN useradd -ms /bin/bash build && echo "build ALL=(ALL) NOPASSWD:ALL" >> /etc/sudoers
RUN echo "en_US.UTF-8 UTF-8" > /etc/locale.gen && locale-gen en_US.UTF-8
ENV LANG en_US.UTF-8
ENV LANGUAGE en_US:en
ENV LC_ALL en_US.UTF-8
RUN usermod -u ${UID} build && groupmod -g ${GID} build

USER build
WORKDIR /home/build
CMD ["/bin/bash"]
