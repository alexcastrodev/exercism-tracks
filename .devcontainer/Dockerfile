FROM ruby:3.3.9

# Install build dependencies and SQLite development libraries
RUN apt-get update -qq && apt-get install -y \
    libpq-dev \
    build-essential \
    libsqlite3-dev

# Install Node.js and Yarn
RUN curl -fsSL https://deb.nodesource.com/setup_23.x | bash - && \
    apt-get install --no-install-recommends -y nodejs && \
    npm install -g yarn && \
    rm -rf /var/lib/apt/lists /var/cache/apt/archives

RUN gem install minitest

# Create a non-root user "vscode"
RUN useradd -ms /bin/bash vscode

# Set the working directory and change ownership to the vscode user
WORKDIR /workspace
RUN chown -R vscode:vscode /workspace

# Switch to the non-root user
USER vscode