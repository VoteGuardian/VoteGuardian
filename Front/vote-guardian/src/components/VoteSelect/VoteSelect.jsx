import { voteInfo } from "@/recoil/atoms/voteAtoms";
import Link from "next/link";
import { useSetRecoilState } from "recoil";
import Button from "../Common/Button/Button";
import './VoteSelect.scss'

export default function VoteSelect() {
    const setVote = useSetRecoilState(voteInfo);
    function handleVoteType(e) {
        //현재 선택한 투표가 어떤 투표인지
        const voteType = e.target.className;
        //찬반투표는 true, 선거투표는 false
        if(voteType === 'yn') setVote({type: true});
        else setVote({type: false});
        //선택한 투표 버튼에 색을 입힘
        const voteTypeList = document.getElementsByClassName('vote-style')[0].childNodes;
        for(let i = 0; i < voteTypeList.length; i++) {
            voteTypeList[i].classList.remove('clicked');
        }
        e.target.classList.add('clicked')
    }
    return (
        <div className='vote-create-flex'>
            <div>
                <div className="create-select-title">
                    <div className="title-flex">
                        <p className='title-text'>투표 종류를 선택해주세요</p>
                        <div className='question-wrap'>
                            <p>?</p>
                        </div>
                    </div>
                </div>
                <div className='vote-style'>
                    <p className='yn'onClick={handleVoteType}>찬반 투표</p>
                    <p className='ele'onClick={handleVoteType}>선거 투표</p>
                </div>
                <Link href='' style={{ textDecoration: "none" }}>
                    <Button buttonType='next' text='다음 단계로'></Button>
                </Link>
            </div>
        </div>
    )
}