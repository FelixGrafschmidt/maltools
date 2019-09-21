<template>
	<section class="">
		<section class="level">
			<form class="container level-left">
				<label class="field">
					Your MAL username
					<div class="level">
						<input
							class="input is-rounded is-primary is-left control"
							type="text"
							placeholder="Type here"
							ref="userNameInput"
						/>
						<button
							class="button level-left is-rounded is-right control"
							type="submit"
							@click.prevent="updateAnimeList"
						>
							Show my list
						</button>
					</div>
				</label>
			</form>
		</section>

		<section>
			<!-- Start Carousel -->
			<carousel :perPage="7" class="moe-carousel">
				<slide
					v-for="anime in animeList"
					:key="anime.name"
					class="moe-slide"
				>
					<img
						class="moe-image"
						:src="anime.imageUrl"
						:alt="anime.name"
					/>
				</slide>
			</carousel>
			<!-- End Carousel -->
		</section>
	</section>
</template>

<script lang="ts">
	import axios from "axios";
	import { Component, Vue, Prop } from "vue-property-decorator";
	import { Carousel, Slide } from "vue-carousel";

	import Anime from "../modules/Anime";

	@Component({
		components: {
			Carousel,
			Slide
		}
	})
	export default class AnimeList extends Vue {
		animeList: Array<Anime> = new Array<Anime>();
		currentPage: number = 1;
		userName: string = "";
		updateAnimeList(): void {
			const userNameInput = this.$refs.userNameInput as HTMLInputElement;
			this.userName = userNameInput.value;
			this.newAnimeAjax(this.userName)
				.then(response => {
					const { data } = response;
					this.animeList = data;
					this.$forceUpdate();
				})
				.catch(error => {
					console.log(error);
				});
		}
		newAnimeAjax(userName: string): Promise<any> {
			return axios.get<Array<Anime>>(
				"http://localhost:8081/get-anime-for-username",
				{
					params: {
						userName: this.userName
					}
				}
			);
		}
		mounted() {}
	}
</script>


<style lang="scss" scoped>
	.moe-image {
		width: 200px;
		height: 300px;
	}
	.moe-slide {
		width: 200px;
	}
</style>
