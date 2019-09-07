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
			<div class="container">
				<!-- Start Carousel -->
				<div ref="carousel" class="carousel">
					<div
						:class="'item-' + count"
						v-for="(anime, count) in animeList"
						:key="count"
					>
						<img :src="anime.imageUrl" :alt="anime.name" />
					</div>
				</div>
				<!-- End Carousel -->
			</div>
		</section>
	</section>
</template>

<script lang="ts">
	import axios from "axios";
	import { Component, Vue, Prop } from "vue-property-decorator";
	import carousel from "bulma-carousel";

	import Anime from "../modules/Anime";

	@Component({
		components: {
			carousel
		}
	})
	export default class AnimeList extends Vue {
		@Prop(String)
		incomingUserName: string = "Ithambar";

		_animeList: Array<Anime> = new Array<Anime>();
		get animeList(): Array<Anime> {
			return this._animeList;
		}
		set animeList(animeList: Array<Anime>) {
			this._animeList = animeList;
		}
		currentPage: number = 1;
		userName: string = "";
		updateAnimeList(): void {
			const userNameInput = this.$refs.userNameInput as HTMLInputElement;
			this.userName = userNameInput.value;
			this.newAnimeAjax(this.userName)
				.then(response => {
					const { data } = response;
					this._animeList = data;
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
		get tenEntriesFromList(): Array<Anime> {
			const start = (this.currentPage - 1) * 10;
			return this.animeList.slice(start, start + 10);
		}
		get isFirstPage(): boolean {
			return this.currentPage === 1;
		}
		get isLastPage(): boolean {
			return this.currentPage * 10 >= this.animeList.length;
		}
		previousPage(): void {
			this.currentPage--;
		}
		nextPage(): void {
			this.currentPage++;
		}
		updatePage(page: number): void {
			this.currentPage = page;
		}
		mounted() {
			if (this.incomingUserName !== "") {
				this.userName = this.incomingUserName;
				const userNameInput = this.$refs.userNameInput as HTMLInputElement;
				userNameInput.value = this.userName;
				this.updateAnimeList();
			}
			// carousel.attach(this.$refs.carousel, {
			// 	slidesToScroll: 1,
			// 	slidesToShow: 4
			// });
		}
	}
</script>


<style lang="scss" scoped>
	.moe-image {
		max-width: 100%;
		height: auto;
	}
	.moe-margin-left-10px {
		margin-left: 10px;
	}
	.moe-margin-right-10px {
		margin-right: 10px;
	}
</style>
