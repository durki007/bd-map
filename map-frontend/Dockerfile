FROM node:20-slim AS base
ENV PNPM_HOME="/pnpm"
ENV PATH="$PNPM_HOME:$PATH"
RUN corepack enable
COPY . /app
WORKDIR /app

FROM base AS runner
ARG NEXT_PUBLIC_API_PORT
ARG NEXT_PUBLIC_API_HOST
ENV NEXT_PUBLIC_API_PORT=${NEXT_PUBLIC_API_PORT}
ENV NEXT_PUBLIC_API_HOST=${NEXT_PUBLIC_API_HOST}
RUN --mount=type=cache,id=pnpm,target=/pnpm/store pnpm install --frozen-lockfile
RUN pnpm run build
EXPOSE 3000
CMD [ "pnpm", "start" ]