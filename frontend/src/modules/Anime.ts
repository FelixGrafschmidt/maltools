export default class Anime extends Object {
	private _url: URL;
	private _id: number;
	private _name: string;
	private _imageUrl: URL;

	/**
	 * getUrl
	 */
	public get url(): URL {
		return this._url;
	}
	/**
	 * getId
	 */
	public get id(): number {
		return this._id;
	}
	/**
	 * getName
	 */
	public get name(): string {
		return this._name;
	}
	/**
	 * getImageUrl
	 */
	public get imageUrl(): URL {
		return this._imageUrl;
	}

	constructor(
		url: URL,
		id: number,
		name: string,
		imageUrl: URL,
	) {
		super();
		this._url = url;
		this._id = id;
		this._name = name;
		this._imageUrl = imageUrl;
	}
}
