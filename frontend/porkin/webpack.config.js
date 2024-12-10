const HtmlWebpackPlugin = require("html-webpack-plugin");
const WebpackPwaManifest = require("webpack-pwa-manifest");
const { GenerateSW } = require("workbox-webpack-plugin");
const path = require("path");

module.exports = {
  mode: "development",
  entry: "./src/index.js",
  output: {
    filename: "main.js",
    path: path.resolve(__dirname, "dist"),
    clean: true,
  },
  devtool: "eval-source-map",
  devServer: {
    watchFiles: ["./src/template.html"],
  },
  plugins: [
    new HtmlWebpackPlugin({
      template: "./src/template.html",
      favicon: "./public/favicon/favicon.svg",
    }),
    new WebpackPwaManifest({
      name: "porkin!",
      short_name: "App",
      description: "Organize os gastos do rolê sem medo!",
      background_color: "#ffffff",
      theme_color: "#000000",
      start_url: "/",
      display: "standalone",
      icons: [
        {
          src: path.resolve("./public/favicon/favicon.svg"),
          sizes: [96, 128, 192, 256, 384, 512],
        },
      ],
    }),
    new GenerateSW({
      clientsClaim: true,
      skipWaiting: true,
      maximumFileSizeToCacheInBytes: 5 * 1024 * 1024, // Set to 5 MB
      runtimeCaching: [
        {
          urlPattern: /\.(?:html|css|js|png|jpg|svg|webp)$/,
          handler: "CacheFirst",
          options: {
            cacheName: "static-resources",
          },
        },
      ],
    }),
  ],
  module: {
    rules: [
      {
        test: /\.css$/i,
        use: ["style-loader", "css-loader"],
      },
      {
        test: /\.html$/i,
        loader: "html-loader",
      },
      {
        test: /\.(png|svg|jpg|jpeg|gif)$/i,
        type: "asset/resource",
      },
    ],
  },
};
