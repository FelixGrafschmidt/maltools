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
		<section style="padding-bottom: 2em;" v-if="animeList.length > 10">
			<nav
				class="pagination is-centered is-rounded"
				role="navigation"
				aria-label="pagination"
			>
				<pagination :data="animeList" />
				<!-- <a
					class="pagination-previous"
					v-if="!isFirstPage"
					@click="currentPage--"
				>
					Previous page
				</a>
				<a
					class="pagination-next"
					v-if="!isLastPage"
					@click="currentPage++"
				>
					Next page
				</a> -->
				<!-- <paginate
					:page-count="animeList.length / 10"
					:clickHandler="updatePage"
					:prev-class="'moe-pagination-previous'"
					:next-class="'pagination-next'"
					:active-class="'is-current'"
					:disabled-class="'disabled'"
					:containerClass="'pagination-list'"
					:page-class="'pagination-link'"
					:hide-prev-next="true"
					:first-last-button="true"
					v-model="currentPage"
				/> -->
			</nav>
		</section>
		<section class="columns" style="height: 375px">
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
		</section>
	</section>
</template>

<script lang="ts">
	import axios from "axios";
	import Pagination from "@/components/Pagination.vue";
	import { Component, Vue, Prop } from "vue-property-decorator";

	import Anime from "../modules/Anime";

	@Component({
		components: {
			Pagination
		},
		computed: {
			// getCurrentPage(): number {
			// 	return this.currentPage;
			// },
			// isFirstPage(): boolean {
			// 	return this.currentPage === 1;
			// },
			// isLastPage(): boolean {
			// 	return this.currentPage * 10 >= this.animeList.length;
			// }
		}
	})
	export default class AnimeList extends Vue {
		@Prop(String)
		userName!: String;

		animeList: Array<Anime> = new Array<Anime>();
		currentPage: number = 1;
		updateAnimeList(): void {
			const userNameInput = this.$refs.userNameInput as HTMLInputElement;
			const userName = userNameInput.value;

			axios
				.get<Array<Anime>>("http://localhost:8081/get-anime-for-username", {
					params: {
						userName: userName
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
		updatePage(page: number): void {
			this.currentPage = page;
		}
	}
</script>


<style lang="scss" scoped>
	.moe-image {
		max-width: 100%;
		height: auto;
	}
	.moe-pagination-first {
		order: -2;
	}
	.moe-pagination-last {
		order: 100;
	}
	.moe-pagination-previous {
		order: -1;
	}
	.moe-pagination-next {
		order: 10;
	}
</style>
