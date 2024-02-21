'use client'
import MenuBar from "@/components/Common/MenuBar/MenuBar";
import { PC, Tablet, Mobile } from "@/hooks/useResize";
import './VoteMain.scss';
import Image from "next/image";

export default function VoteMain() {
    //로그인 후 정보..
    const nickname = '김OO';
    const email = 'ko123@g.com';

    //투표 공유하기 버튼을 누를 경우
    function handleLinkCopy(e) {
        //홈페이지 메인페이지의 링크를 복사하게 함(현재는 로컬 테스트용)
        const URL = "localhost:3000"
        navigator.clipboard.writeText(URL).then(res => {
            //일단 복사 완료는 alert창으로(나중에 원할 경우 수정)
            alert("주소가 복사되었습니다.")
        })
    }
    return(
        <>
            <div className="vote-main-wrap">
                <PC>
                    <div className="vote-main-pc-wrap">
                        <MenuBar></MenuBar>
                        <div className="vote-main-flex">
                            <div>
                                <div>
                                    <div className="vote-main-top">
                                        <div className="top-flex">
                                            <div className="top-image">
                                                <Image src="/icon.jpg" alt="icon" layout='fill' objectFit="cover"/>
                                            </div>
                                            <div className="top-text-flex">
                                                <div className="text-text">
                                                    <p>{nickname}</p>
                                                    <p>{email}</p>
                                                    <p onClick={handleLinkCopy}>투표 공유하기</p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div className="vote-main-mid">
                                        <div>
                                            <p className="mid-title">안녕하세요 {nickname}님! 투표 가디언에 대해 알려드릴게요!</p>
                                            <div>
                                                <div className="function-wrap">
                                                    <p className="function">투표 만들기</p>
                                                    <p className="function-detail">투표 만들기로 찬반투표나 선거투표 중에 내가 필요한 투표 스타일에 맞춰 새로운 투표를 생성할 수 있어요!</p>
                                                </div>
                                                <div className="function-wrap">
                                                    <p className="function">투표 참여하기</p>
                                                    <p className="function-detail">투표 참여하기로 진행중인 투표에 참여해서 나의 의견을 반영할 수 있어요!</p>
                                                    <p className="function-detail">블록체인 기반의 안전한 투표 시스템에서 마음껏 의견을 전달할 수 있어요!</p>
                                                </div>
                                                <div className="function-wrap">
                                                    <p className="function">투표 관리하기</p>
                                                    <p className="function-detail">투표 관리하기로 내가 만든 투표 내용을 수정하거나 삭제할 수 있어요!</p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </PC>
                <Tablet>
                    <div className="vote-main-tablet-wrap">
                        <MenuBar></MenuBar>
                    </div>
                </Tablet>
                <Mobile>
                    <div className="vote-main-mobile-wrap">
                        <MenuBar></MenuBar>
                    </div>
                </Mobile>
            </div>
        </>
    )
}