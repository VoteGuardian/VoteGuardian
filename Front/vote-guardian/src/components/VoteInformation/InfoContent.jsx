
import { voteInfo } from "@/recoil/atoms/voteAtoms"
import { useRecoilValue } from "recoil"
import './InfoContent.scss'
import { useEffect } from "react";
import Link from "next/link";
import Button from "../Common/Button/Button";
import { useRouter } from "next/navigation";


export default function InfoContent() {
    const voteType = useRecoilValue(voteInfo);
    const currentTime = new Date();
    const router = useRouter();

    let titleText = '';
    let contentText = '';

    function handleTitleChange(e) {
        titleText = e.target.value;
        //제목은 40글자 제한
        if(titleText.length > 40) {
            document.getElementsByClassName("title-error")[0].classList.add('show');
        }
        else document.getElementsByClassName("title-error")[0].classList.remove('show');
    }

    function handleContentChange(e) {
        contentText = e.target.value;
        if(contentText.length > 1100) {
            document.getElementsByClassName("content-error")[0].classList.add('show');
        }
        else document.getElementsByClassName("content-error")[0].classList.remove('show');
    }

    function handleVoteInfo() {
        //투표 제목과 정보의 내용의 길이가 조건을 충족했을 경우
        if(0 < titleText.length && titleText.length <= 40 && 0 < contentText.length && contentText.length <= 1100) {
            router.push('/VoteCreate/Voter')
        }
        //충족되지 않았을 경우 알람을 띄워줘야함
        else {
            console.log('조건이 충족되지 않음')
        }
    }

    useEffect(() => {
    }, [])

    return (
        <>
            <div className="vote-content-flex">
                <div>
                    <div className="vote-type-wrap">
                    {voteType.type === true ?
                        <p>찬반 투표</p>
                        : <p>선거 투표</p>}
                    </div>
                    <div className="vote-date">
                        <p className="info-title">투표 기간</p>
                        <div className="date-flex">
                            <input type="datetime-local" className="time-start"></input>
                            <input type="datetime-local"></input>
                        </div>
                    </div>
                    <div className="vote-title">
                        <p className="info-title">투표 제목</p>
                        <div className="title-input-wrap">
                            <input onInput={handleTitleChange} type="text" placeholder="투표 제목을 입력하세요"/>
                        </div>
                        <p className="title-error">투표 제목은 40글자 이하여야 합니다</p>
                    </div>
                    <div className="vote-info">
                        <p className="info-title">투표 정보</p>
                        <div className="info-input-wrap">
                            <textarea onInput={handleContentChange}name="content" cols="50" rows="22" placeholder="투표에 대해서 설명할 내용을 작성하세요"/>
                        </div>
                        <p className="content-error">투표 정보는 1100글자 이하여야 합니다</p>
                    </div>
                    {voteType.type === false &&
                    <div className="vote-candidate">
                        <p className="info-title">투표 후보 등록</p>
                        <div className="candidate-plus">
                            <p>+</p>
                        </div>
                    </div>
                    }
                    <div className="button-group-flex">
                        <Link href='' style={{ textDecoration: "none" }}>
                            <Button buttonType='select positive' text='이전'></Button>
                        </Link>
                        <div onClick={handleVoteInfo}>
                            <Button buttonType='select negative' text='다음'></Button>
                        </div>
                    </div>
                </div>
            </div>
        </>
    )
}