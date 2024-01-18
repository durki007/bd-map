FROM node:21-alpine as base

FROM base as runner
WORKDIR /app
COPY package*.json ./
RUN pnpm install
COPY . .
EXPOSE 3000

