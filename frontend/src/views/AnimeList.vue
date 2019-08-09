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
							@click.prevent="updateAnimeList()"
						>
							Show my list
						</button>
					</div>
				</label>
			</form>
		</section>

		<section class="columns is-vcentered" style="height: 375px">
			<button
				:disabled="isFirstPage === true"
				class="button moe-margin-left-10px"
				@click="previousPage"
			>
				Previous
			</button>
			<a
				class="column"
				v-for="anime in tenEntriesFromList"
				:href="anime.url"
				target="_blank"
				:key="anime.id"
			>
				<h3 style="height: 70px;" class="title is-6">
					{{ anime.name }}
				</h3>
				<img
					class="moe-image"
					:src="anime.imageUrl"
					:alt="anime.name"
				/>
			</a>
			<button
				:disabled="isLastPage === true"
				class="button moe-margin-right-10px"
				@click="nextPage"
			>
				Next
			</button>
		</section>
	</section>
</template>

<script lang="ts">
	import axios from "axios";
	import { Component, Vue, Prop } from "vue-property-decorator";

	import Anime from "../modules/Anime";

	@Component({})
	export default class AnimeList extends Vue {
		@Prop(String)
		incomingUserName: string = "Ithambar";

		animeList: Array<Anime> = new Array<Anime>();
		currentPage: number = 1;
		userName: string = "";
		updateAnimeList(): void {
			const userNameInput = this.$refs.userNameInput as HTMLInputElement;
			this.userName = userNameInput.value;

			axios
				.get<Array<Anime>>("http://localhost:8081/get-anime-for-username", {
					params: {
						userName: this.userName
					}
				})
				.then(response => {
					const { data } = response;
					this.animeList = data;
				})
				.catch(error => {
					console.log(error);
				});
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
