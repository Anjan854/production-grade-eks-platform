# Copyright 2020 Google LLC
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

# Define a default value so it's not empty if the builder fails to provide it

FROM node:20-slim AS builder

WORKDIR /usr/src/app

# Install build tools (Debian-based)
RUN apt-get update && apt-get install -y \
    python3 \
    make \
    g++ \
    && rm -rf /var/lib/apt/lists/*

COPY package*.json ./
RUN npm install --omit=dev

COPY . .

# ---------------- runtime stage ----------------
FROM node:20-slim

WORKDIR /usr/src/app

COPY --from=builder /usr/src/app /usr/src/app

# optional user (recommended)
RUN useradd -m appuser && chown -R appuser:appuser /usr/src/app
USER appuser

EXPOSE 50051

CMD ["node", "index.js"]
