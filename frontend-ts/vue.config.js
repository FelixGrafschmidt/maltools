module.exports = {
	runtimeCompiler: true,
	css: {
		sourceMap: true,
	},
	devServer: {
		port: 8081,
		proxy: {
			"/good-anime": {
				target: "http://localhost:8081",
				secure: false,
			},
		},
	},
};
