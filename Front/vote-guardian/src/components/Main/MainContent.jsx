import Image from 'next/image'
import './MainContent.scss'

export default function MainContent() {
    return (
        <>
            <div className="main-content-wrap">
                <div className="main-title-wrap">
                    <span className="main-title-vote">투표</span>
                    <span className="main-title-guardian">가디언</span>
                </div>
                <div>
                    <div className="service-feature1-wrap">
                        <p className='feature1-title'>블록체인을 이용하여 투명하고 신뢰할 수 있는 투표 시스템 아래에서 투표할 수 있어요</p>
                        <div className="feature1-detail">
                            <p>믿을 수 있는 투표 시스템</p>
                            <p>투표가디언의 투표 결과는 퍼블릭 블록체인에 저장되어 투명하게 관리되고 투표 결과 조작이 불가능합니다</p>
                            <div className='feature1-group'>
                                <Image src="/blockchain.jpg" width={300} height={300} alt="blockchain"/>
                                <Image src="/vote.png" width={300} height={300} alt="vote"/>
                            </div>
                            
                        </div>
                    </div>
                    <div className='service-feature2-wrap'>
                        <p className='feature2-title'>나에게 필요한 투표를 생성할 수 있어요</p>
                        <div className='feature2-detail-flex'>
                            <div className='feature2-detail1'>
                                <p>찬반 투표와 선거 투표</p>
                                <p>후보의 유무에 따라 원하는 투표 스타일을 선택할 수 있어요</p>
                                <div className='vote-style'>
                                    <p>찬반 투표</p>
                                    <p>선거 투표</p>
                                </div>
                            </div>
                            <div className='feature2-detail2'>
                                
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </>
    )
}